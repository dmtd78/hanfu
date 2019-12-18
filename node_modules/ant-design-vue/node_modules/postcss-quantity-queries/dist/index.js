'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }(); /* eslint-disable no-param-reassign, no-use-before-define, consistent-return */

var _postcss = require('postcss');

var _postcss2 = _interopRequireDefault(_postcss);

var _balancedMatch = require('balanced-match');

var _balancedMatch2 = _interopRequireDefault(_balancedMatch);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

exports.default = _postcss2.default.plugin('postcss-quantity-queries', function () {
  return function (css) {
    css.walk(function (node) {
      if (node.type === 'rule') {
        return processRule(node);
      }
      if (node.type === 'atrule') {
        return processAtRule(node);
      }
    });
  };
});


var rePseudo = /(.*)(?::{1,2})(at-(?:least|most)|between|exactly)/;
var reAtRule = /(at-(?:least|most)|between|exactly)/;

function processRule(rule) {
  if (!rePseudo.test(rule.selector)) return;

  rule.selectors = rule.selectors.map(function (s) {
    var _balanced = (0, _balancedMatch2.default)('(', ')', s),
        pre = _balanced.pre,
        body = _balanced.body;

    var args = _postcss.list.comma(body);

    var _pre$split = pre.split(/:{1,2}/),
        _pre$split2 = _slicedToArray(_pre$split, 2),
        selector = _pre$split2[0],
        quantifier = _pre$split2[1];

    return quantifiers[quantifier].apply(quantifiers, _toConsumableArray(args))([selector]);
  });
}

function processAtRule(atRule) {
  if (!reAtRule.test(atRule.name)) return;

  var args = _postcss.list.space(atRule.params);
  var parent = atRule.parent;
  var root = parent.root();
  var selectors = quantifiers[atRule.name].apply(quantifiers, _toConsumableArray(args))(parent.selectors);

  var newRule = _postcss2.default.rule({
    selectors,
    nodes: atRule.nodes,
    source: atRule.source,
    raws: {
      semicolon: true
    }
  });

  cleanIndent(newRule);

  root.insertAfter(parent, newRule);
  atRule.remove();

  if (!parent.nodes.length) parent.remove();
}

var cleanIndent = function cleanIndent(rule) {
  return rule.walkDecls(function (decl) {
    decl.raws.before = decl.raws.before.replace(/[^\S\x0a\x0d]{2,}/, '  ');
  });
};

var quantitySelectors = function quantitySelectors(quantifier, last) {
  return function (selectors) {
    return selectors.map(function (s) {
      return `${s}${quantifier}, ${s}${quantifier} ~ ${last || _postcss.list.space(s).pop()}`;
    });
  };
};

var quantifiers = {

  'at-least': function atLeast(count, last) {
    return quantitySelectors(`:nth-last-child(n+${count})`, last);
  },

  'at-most': function atMost(count, last) {
    return quantitySelectors(`:nth-last-child(-n+${count}):first-child`, last);
  },

  between: function between(start, end, last) {
    return quantitySelectors(`:nth-last-child(n+${start}):nth-last-child(-n+${end}):first-child`, last);
  },

  exactly: function exactly(count, last) {
    return quantitySelectors(`:nth-last-child(${count}):first-child`, last);
  }

};
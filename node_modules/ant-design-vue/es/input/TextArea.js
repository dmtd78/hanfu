import _mergeJSXProps from 'babel-helper-vue-jsx-merge-props';
import _defineProperty from 'babel-runtime/helpers/defineProperty';
import _extends from 'babel-runtime/helpers/extends';
import classNames from 'classnames';
import omit from 'omit.js';
import ResizeObserver from 'resize-observer-polyfill';
import inputProps from './inputProps';
import calculateNodeHeight from './calculateNodeHeight';
import hasProp from '../_util/props-util';
import { ConfigConsumerProps } from '../config-provider';

function onNextFrame(cb) {
  if (window.requestAnimationFrame) {
    return window.requestAnimationFrame(cb);
  }
  return window.setTimeout(cb, 1);
}

function clearNextFrameAction(nextFrameId) {
  if (window.cancelAnimationFrame) {
    window.cancelAnimationFrame(nextFrameId);
  } else {
    window.clearTimeout(nextFrameId);
  }
}
function fixControlledValue(value) {
  if (typeof value === 'undefined' || value === null) {
    return '';
  }
  return value;
}
function noop() {}

export default {
  name: 'ATextarea',
  model: {
    prop: 'value',
    event: 'change.value'
  },
  props: _extends({}, inputProps, {
    autosize: [Object, Boolean]
  }),
  inject: {
    configProvider: { 'default': function _default() {
        return ConfigConsumerProps;
      } }
  },
  data: function data() {
    var _$props = this.$props,
        _$props$value = _$props.value,
        value = _$props$value === undefined ? '' : _$props$value,
        _$props$defaultValue = _$props.defaultValue,
        defaultValue = _$props$defaultValue === undefined ? '' : _$props$defaultValue;

    return {
      stateValue: fixControlledValue(!hasProp(this, 'value') ? defaultValue : value),
      nextFrameActionId: undefined,
      textareaStyles: {}
    };
  },

  computed: {},
  watch: {
    value: function value(val) {
      var _this = this;

      this.$nextTick(function () {
        _this.resizeOnNextFrame();
      });
      this.stateValue = fixControlledValue(val);
    },
    autosize: function autosize(val) {
      if (!val && this.$refs.textArea) {
        this.textareaStyles = omit(this.textareaStyles, ['overflowY']);
      }
    }
  },
  mounted: function mounted() {
    var _this2 = this;

    this.$nextTick(function () {
      _this2.resizeTextarea();
      _this2.updateResizeObserverHook();
      if (_this2.autoFocus) {
        _this2.focus();
      }
    });
  },
  updated: function updated() {
    this.updateResizeObserverHook();
  },
  beforeDestroy: function beforeDestroy() {
    if (this.resizeObserver) {
      this.resizeObserver.disconnect();
    }
  },

  methods: {
    resizeOnNextFrame: function resizeOnNextFrame() {
      if (this.nextFrameActionId) {
        clearNextFrameAction(this.nextFrameActionId);
      }
      this.nextFrameActionId = onNextFrame(this.resizeTextarea);
    },

    // We will update hooks if `autosize` prop change
    updateResizeObserverHook: function updateResizeObserverHook() {
      if (!this.resizeObserver && this.$props.autosize) {
        // Add resize observer
        this.resizeObserver = new ResizeObserver(this.resizeOnNextFrame);
        this.resizeObserver.observe(this.$refs.textArea);
      } else if (this.resizeObserver && !this.$props.autosize) {
        // Remove resize observer
        this.resizeObserver.disconnect();
        this.resizeObserver = null;
      }
    },
    handleKeyDown: function handleKeyDown(e) {
      if (e.keyCode === 13) {
        this.$emit('pressEnter', e);
      }
      this.$emit('keydown', e);
    },
    resizeTextarea: function resizeTextarea() {
      var autosize = this.$props.autosize;

      if (!autosize || !this.$refs.textArea) {
        return;
      }
      var minRows = autosize.minRows,
          maxRows = autosize.maxRows;

      var textareaStyles = calculateNodeHeight(this.$refs.textArea, false, minRows, maxRows);
      this.textareaStyles = textareaStyles;
    },
    handleTextareaChange: function handleTextareaChange(e) {
      var _e$target = e.target,
          value = _e$target.value,
          composing = _e$target.composing;

      if (composing || this.stateValue === value) return;
      if (!hasProp(this, 'value')) {
        this.stateValue = value;
        this.resizeTextarea();
      } else {
        this.$forceUpdate();
      }

      this.$emit('change.value', value);
      this.$emit('change', e);
      this.$emit('input', e);
    },
    focus: function focus() {
      this.$refs.textArea.focus();
    },
    blur: function blur() {
      this.$refs.textArea.blur();
    }
  },
  render: function render() {
    var h = arguments[0];
    var stateValue = this.stateValue,
        handleKeyDown = this.handleKeyDown,
        handleTextareaChange = this.handleTextareaChange,
        textareaStyles = this.textareaStyles,
        $attrs = this.$attrs,
        $listeners = this.$listeners,
        customizePrefixCls = this.prefixCls,
        disabled = this.disabled;

    var otherProps = omit(this.$props, ['prefixCls', 'autosize', 'type', 'value', 'defaultValue', 'lazy']);
    var getPrefixCls = this.configProvider.getPrefixCls;
    var prefixCls = getPrefixCls('input', customizePrefixCls);

    var cls = classNames(prefixCls, _defineProperty({}, prefixCls + '-disabled', disabled));

    var textareaProps = {
      directives: [{ name: 'ant-input' }],
      attrs: _extends({}, otherProps, $attrs),
      on: _extends({}, $listeners, {
        keydown: handleKeyDown,
        input: handleTextareaChange,
        change: noop
      })
    };
    return h('textarea', _mergeJSXProps([textareaProps, {
      domProps: {
        'value': stateValue
      },

      'class': cls,
      style: textareaStyles,
      ref: 'textArea'
    }]));
  }
};
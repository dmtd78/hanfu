<template>
    <ul>
        <li>
            <a href="http://www.17sucai.com/">
                <h2>Baici Liu:</h2>
                <p>小米的饥饿营销真是太坑爹了！ball shit!</p>
            </a>
        </li>
        <li>
            <a href="http://www.17sucai.com/">
                <h2>Xiaoqiang Yan:</h2>
                <p>最近怎么没有妹纸发帖呢?recommend!recommend!</p>
            </a>
        </li>
        <li>
            <a href="http://www.17sucai.com/">
                <h2>Pan Huang:</h2>
                <p>好饿啊！求带！hungry！hungry！</p>
            </a>
        </li>
        <li>
            <a href="http://www.17sucai.com/">
                <h2>Feng Deng:</h2>
                <p>哎哟，那妹纸不错来着，看我去问个电话号码回来！come on!</p>
            </a>
        </li>
        <li>
            <a href="http://www.17sucai.com/">
                <h2>star 张:</h2>
                <p>好好学习，天天向上！good good study!day day up!</p>
            </a>
        </li>
        <li>
            <a href="http://www.17sucai.com/">
                <h2>某武林高手:</h2>
                <p>低于25000/month的面试再也不去了，他grandma的！</p>
            </a>
        </li>
    </ul>
</template>

<script>
    export default {
        name: "Node"
    }
</script>

<style scoped>
    .note ul{
        margin:0px 325px;
        width:100%;
        overflow:hidden;
        padding:3em;
    }
    .note ul li{
        float:left;
        margin:1em;
    }
    .note ul li a{
        color:#000;
        text-decoration:none;
        background:#ffc;
        display:block;
        height:10em;
        width:10em;
        padding:1em;
        -moz-box-shadow:5px 5px 7px rgba(33,33,33,1);
        -webkit-box-shadow:5px 5px 7px rgba(33,33,33,7);
        box-shadow:5px 5px 7px rgba(33,33,33,7);
        -webkit-transform:rotate(-6deg);
        -moz-transform:rotate(-6deg);
        -o-transform:rotate(-6deg);

        -moz-transition:-moz-transform .15s linear;
        -o-transition:-o-transform .15s linear;
        -webkit-transition:-webkit-transform .15s linear;

    }
    .note ul li h2{
        font-size:140%;
        font-weight:bold;
        padding-bottom:10px;
    }
    .note ul li p{
        font-family: "Reenie Beanie" ,arial,sans-serif,Î¢ÈíÑÅºÚ;
        font-size:110%;
    }
    .note ul li:nth-child(odd) a{
        -webkit-transform:rotate(-4deg);
        -moz-transform:rotate(-4deg);
        -o-transform:rotate(-4deg);
        position:relative;
        top:5px;
    }
    .note ul li:nth-child(3n) a{
        -webkit-transform:rotate(5deg);
        -moz-transform:rotate(5deg);
        -o-transform:rotate(5deg);
        position:relative;
        top:5px;
        background:#cfc;

    }
    .note ul li:nth-child(5n) a{
        -webkit-transform:rotate(7deg);
        -moz-transform:rotate(7deg);
        -o-transform:rotate(7deg);
        position:relative;
        top:5px;
        background:#ccf;
    }
    .note ul li a:hover,ul li a:focus{
        -moz-box-shadow:10px 10px 7px rgba(0,0,0,7);
        -webkit-box-shadow:10px 10px 7px rgba(0,0,0,7);
        box-shadow:10px 10px 7px rgba(0,0,0,7);
        -webkit-transform:scale(1.25);
        -moz-transform:scale(1.25);
        -o-transform:scale(1.25);
        transform:scale(1.25);
        position:relative;
        z-index:5;

    }
</style>
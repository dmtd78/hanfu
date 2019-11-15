<div class="hello">
  <h1>{{ msg }}</h1>
</div>

<script>
  export default {
      data(){
          return{
              isCollapsed:false,
              backstage:[],
              list:[],
              msg: '论坛'
          }
      },

      created() {
          this.$http('http://localhost:8081/article/list/1').then((reg)=>{
              console.log(reg);
          })
      }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>

<template>
  <nav class="menu">
    <ul class="rotas">
      <div v-for="rota in rotas">
        <el-button type="text"> <router-link :to="rota.path ? rota.path : '/'">{{ rota.titulo }}</router-link></el-button>
      </div>
    </ul>
  </nav>
</template>

<script>

export default {
  props: ['rotas'],
  data(){
      return{

      }

  }
}
</script>

<style scoped>
  .menu{
    background-color: #66B032;
    height: 90px;
  }
  .rotas{
    list-style: none;
    line-height: 20px;
    color: aqua;
  }
  a{
    color: #F0F7D4;
    font-size: 18px;
    font-family: Helvetica, sans-serif;
    font-weight: bold;
    text-decoration-line: none;
  }
</style>

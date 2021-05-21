<template>
  <div class="pai">
    <meu-menu :rotas="routes"/>
    <div class="corpo">
      <transition name="pagina">
        <router-view>

        </router-view>
      </transition>
    </div>
</div>
</template>

<script>
import {routes} from './routes';
import Menu from './components/shared/menu/Menu.vue';

export default {
  components:{
    'meu-menu': Menu,
  },
  data(){
    return{
      routes: routes
    }
  }
}

</script>

<style scoped>
  .corpo{
    font-family: Helvetica, sans-serif;
    width: 100%;
    margin: 0 auto;
    /*padding-inline: 50px;*/
    background-color: #F0F7D4;
  }
  .pagina-enter, pagina-leave-active{
    opacity: 0;
  }
  .pagina-enter-active, pagina-leave-active{
    transition: opacity 1s;

  }
  .pai{
    background-color: #F0F7D4;

  }
</style>

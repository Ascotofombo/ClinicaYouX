<template>
  <button :class=estiloBotao @click="disparaAcao()" :type="tipo">{{ rotulo }}</button>
</template>

<script>
export default {
  data(){
    return{

    }
  },
  methods:{
    disparaAcao(){

      if(this.confirmacao){
        if(confirm("")) {
          this.$emit('botaoAtivado');
        }
        return;
      }
      this.$emit('botaoAtivado');

    }
  },

  computed: {

    estiloBotao(){
        if(this.estilo=="padrao" || !this.estilo) return 'botao botao-padrao';
        if(this.estilo=="perigo") return 'botao botao-perigo';

    }
  },
  props: ['tipo','rotulo','confirmacao','estilo']
}
</script>

<style scoped>
.botao {
  display: inline-block;
  padding: 10px;
  border-radius: 3px;
  margin: 10px;
  font-size: 1.2em;
}

.botao-perigo {
  background: firebrick;
  color: white;
}

.botao-padrao {
  background: darkcyan;
  color: white;
}
</style>

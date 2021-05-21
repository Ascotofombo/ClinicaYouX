<template>

  <div>
    <h2 class="centralizado"> Cadastrar Paciente</h2>
    <form class="formulario">
      <!--String nome, String cpf, LocalDate nasc, Double altura, String uf-->
      <div class="campo">
        <label for="nome">Nome
          <span class="obr">*</span>
        </label>
        <el-input v-model="paciente.nome" id="nome"></el-input>
      </div>

      <div class="campo">
        <label for="cpf">CPF
          <span class="obr">*</span>
        </label>
        <el-input v-mask="['###.###.###-##']" v-model="paciente.cpf" id="cpf"></el-input>
      </div>
      <div class="campo">
          <label>Data de Nascimento</label>
          <el-date-picker
            v-model="paciente.nasc"
            type="date"
            placeholder="Selecione a data">
          </el-date-picker>
      </div>
      <div class="campo">
        <label for="altura">Altura</label>
        <el-input v-mask="['#.##']" v-model="paciente.altura" id="altura"></el-input>
      </div>
      <div class="campo">
        <label for="peso">Peso</label>
        <el-input v-mask="['##.##','#.##','###.##']" v-model="paciente.peso" id="peso"></el-input>
      </div>
      <div class="campo">
        <label>UF
          <span class="obr">*</span>
        </label>
        <el-select v-model="paciente.uf" placeholder="Selecione a UF"><span class="obr">*</span>
          <el-option
            v-for="item in ufs"
            :key="item.id"
            :label="item.sigla"
            :value="item.sigla">
          </el-option>
        </el-select>
      </div>
      <div class="centralizado">
        <meu-botao rotulo="GRAVAR" tipo="submit" @botaoAtivado="gravar(paciente)"/>
        <router-link to="/"><meu-botao rotulo="VOLTAR" tipo="button"/></router-link>
      </div>


    </form>
  </div>
</template>

<script>
import Botao from '../shared/botao/Botao.vue';


export default {

  data(){
    return{
      paciente:{

      },
      ufs:[]
    }
  },

  components:{
    'meu-botao': Botao
  },

  methods:{
    gravar(paciente){
      if(this.validar()){
        this.$http.post("http://localhost:8081/paciente/newPaciente",paciente)
          .then((result => {
            alert("Paciente Cadastrado!");
          }));
      }else{
        alert("Dados obrigatorios nao preenchidos!");
      }

    },
    validar(){
       if(!this.paciente.nome || !this.paciente.cpf || !this.paciente.uf) return false;
       return true;
    }
  },

  mounted(){
    this.$http.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados")
      .then((result => {
        this.ufs = result.body;
        console.log(this.ufs);
      }));
  }

}
</script>

<style scoped>
  .centralizado{
    text-align: center;
  }
  .campo {
    font-size: 1.2em;
    margin-bottom: 20px;

  }
  .campo label {
    display: block;
    font-weight: bold;
  }

  .campo label + input, .campo textarea {
    width: 100%;
    font-size: inherit;
    border-radius: 5px
  }
  .obr{
    color: red;
  }
</style>

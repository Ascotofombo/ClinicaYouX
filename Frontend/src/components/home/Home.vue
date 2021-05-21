<template>


  <div>

    <h1 class="centralizado">{{ titulo }}</h1>

    <el-table
      :data="pacientes"
      style="width: 100%"
      align="center"
      max-height="300">
      <el-table-column
        fixed
        prop="nome"
        label="Nome">
      </el-table-column>
      <el-table-column
        prop="cpf"
        label="CPF">
      </el-table-column>
      <el-table-column
        prop="nasc"
        label="Nascimento">
      </el-table-column>
      <el-table-column
        prop="altura"
        label="Altura">
      </el-table-column>
      <el-table-column
        prop="peso"
        label="Peso">
      </el-table-column>
      <el-table-column
        prop="uf"
        label="UF">
      </el-table-column>
      <el-table-column
        align="center"
        fixed="right"
        label="Deletar"
        width="120">
        <template slot-scope="scope">
          <el-button

            @click.native.prevent="remove(scope.row, scope.$index)"
            type="text"
            size="small">
            X
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <meu-mapa/>
    </div>
  </div>

</template>

<script>
import Botao from '../shared/botao/Botao.vue';
import Mapa from '../mapa/Mapa.vue';

export default {
  components:{
    'meu-botao': Botao,
    'meu-mapa': Mapa
  },
  data(){
    return{
      titulo:'Cadastro de Pacientes',
      pacientes:[],
      filtro:''
    }
  },
  created(){
    this.$http.get('http://localhost:8081/paciente/getAll')
      .then(res => res.json())
      .then(pacientes => this.pacientes = pacientes, err => console.log(err));
  },
  computed:{
    pacienteComFiltro(){
      if (this.filtro){
        //filtrar
        let exp = new RegExp(this.filtro.trim(),'i');
        return this.pacientes.filter(paciente => exp.test(paciente.nome))

      }else{
        //lista inteira
        return this.pacientes;
      }
    }
  },
  methods:{
    remove(paciente, pos){
      console.log(paciente.id);
        this.$http.delete("http://localhost:8081/paciente/deleta/"+ paciente.id)
          .then((result => {
              this.pacientes.splice(pos,1);
          }));

      },


    }

}
</script>

<style>

.centralizado{
  text-align: center;
  color: #092834;
}

.el-table th, .el-table tr{
  background-color: #F0F7D4;
}

.el-table thead{
  color:#092834;
}
</style>

<template>
  <div style="height: 80vh">
    <LMap :zoom="zoom" :center="center">
      <LTileLayer :url="url"></LTileLayer>

      <div>
        <l-circle-marker v-for="c in coordes"
          :key="c.sigla"
          :radius="buscaUF(c)"
          :lat-lng="[c.latitude,c.longitude]"
          :weight="1"
        />
      </div>
    </LMap>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker } from "vue2-leaflet";
import LCircleMarker from "vue2-leaflet/dist/components/LCircleMarker";

export default {
  name: "Map",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LCircleMarker
  },
  data() {
    return {
      coordes:[],
      UFs: [],
      url: "https://{s}.tile.osm.org/{z}/{x}/{y}.png",
      zoom: 4,
      center: [-15.611451117373951, -57.06979876997709],
      bounds: null,
      rad:10,
    };
  },
  mounted(){
      this.$http.get("https://raw.githubusercontent.com/kelvins/Municipios-Brasileiros/main/json/estados.json")
        .then((result => {
          this.coordes = JSON.parse(result.body);

        }));
      this.buscaQtdUF();

  },
  computed:{

  },
  methods:{
    buscaQtdUF(){

      this.$http.get("http://localhost:8081/paciente/getQtdUF")
        .then((result => {
          this.UFs = result.body;
     }))

    },
    buscaUF(c){

      let achei=0.3;
      for(var i=0;i<this.UFs.length;i++){

        if(this.UFs[i].label === c.uf){

          achei = this.UFs[i].qtd;


        }

      }
      return Number(achei*10);
    }
  }
};
</script>

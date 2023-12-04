<template>
{{vots}}
 <Bar v-if="this.loaded"
    id="my-chart-id"
    :options="chartOptions"
    :data="chartData"
  />
</template>
<script>
import { useAppStore } from '@/store/app'
const pinia = useAppStore();
import { Bar } from 'vue-chartjs'
  import { watch } from 'vue'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
import { getVotacions } from '@/communicationsManager.js'
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

watch(
  () => pinia.$state.infoVotos.votos,
  (votos) => {
    console.log(`count is: ${votos}`)
  }, { deep: true }
)
export default {
  name: 'BarChart',
  components: { Bar },
  data() {
    return {
        loaded: false,
      chartData: {
        labels: [ 'Vots 1', 'Vots 2', 'Vots 3', 'Vots 4' ],
        datasets: [ { data: [] } ]
      },
      chartOptions: {
        responsive: true
      }
    }
  },
  methods: {
    async recarregar(){
    this.chartData.datasets[0].data = await getVotacions()
    this.loaded = true
    }
  },
  created(){
    this.recarregar()
  },
  computed:{
    vots(){
        return pinia.$state.infoVotos.votos
    },
  },

  
}
</script>
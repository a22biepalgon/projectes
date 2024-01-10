<template>
{{ vots }}
<Bar v-if="this.loaded" id="my-chart-id" :options="chartOptions" :data="chartData" :key="chartKey" />
</template>

<script>
import {
    useAppStore
} from '@/store/app'
const pinia = useAppStore();
import {
    Bar
} from 'vue-chartjs'
import {
    watch
} from 'vue'
import {
    Chart as ChartJS,
    Title,
    Tooltip,
    Legend,
    BarElement,
    CategoryScale,
    LinearScale
} from 'chart.js'
import {
    getVotacions
} from '@/communicationsManager.js'
import {
    reactive
} from 'vue'
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
    name: 'BarChart',
    components: {
        Bar
    },
    data() {
        return {
            loaded: false,
            chartData: {
                labels: ['Vots 1', 'Vots 2', 'Vots 3', 'Vots 4'],
                datasets: [{
                    data: pinia.$state.infoVotos.votos
                }]
            },
            chartOptions: {
                responsive: true
            },
            chartKey: 0
        }
    },
    methods: {
        async recarregar() {
            this.chartData.datasets[0].data = await getVotacions()
            this.loaded = true
            this.chartkey += 1;
        },
        recarregar2(votos) {
            console.log(`count is: ${votos}`)
            this.chartKey += 1;
            this.chartData.datasets[0].data = votos
        }
    },
    created() {
        this.recarregar()
    },
    computed: {
        vots() {
            return pinia.$state.infoVotos.votos
        },
    },

    setup() {
        watch(
            () => pinia.$state.infoVotos.votos,
            (votos) => {
                console.log("AAAAAAAAAAAAAAAAAAAA")
            }, {
                deep: true
            }
        )
    }

}
</script>

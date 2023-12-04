<template>
    {{ votes }}
    <Pie :data="votes" :data.labels="this.labels" />
</template> 
<script>
import { useAppStore } from '@/store/app'
const pinia = useAppStore();
import { Pie } from 'vue-chartjs'
import { getVotacions } from '@/communicationsManager'
import { ArcElement, Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, ArcElement)
export default {
    name: "Charts",
    components: { Pie },
    data() {
        return {
            loaded: false,
            labels: [
                'Vot 1',
                'Vot 2',
                'Vot 3',
                'Vot 4'
            ],


        };
    },

    methods: {

    },
    created() {

    },
    computed: {
        votes() {
            return pinia.$state.infoVotos.votos
        }
    }
}
</script>
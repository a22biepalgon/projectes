<template>
    <v-container>
        <v-row>
            <v-col cols="6">
                <h1>{{ this.vots[0] }}</h1><v-btn @click="this.votar(0)">VOTAR 1</v-btn>
            </v-col>
            <v-col cols="6">
                <h1>{{ this.vots[1] }}</h1><v-btn @click="this.votar(1)">VOTAR 2</v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6">
                <h1>{{ this.vots[2] }}</h1><v-btn @click="this.votar(2)">VOTAR 3</v-btn>
            </v-col>
            <v-col cols="6">
                <h1>{{ this.vots[3] }}</h1><v-btn @click="this.votar(3)">VOTAR 4</v-btn>
            </v-col>

        </v-row>
    </v-container>
</template>

<script>
import { socket, state } from "@/socket";
import { useAppStore } from '@/store/app'
import { getVotacions } from '@/communicationsManager'

const pinia = useAppStore();

export default {
    name: "Botons",
    data() {
        return {
            vots: []
        };
    },
    methods: {

        connectar() {
            socket.connect();
        },
        desconnectar() {
            socket.disconnect();
        },

        votar(id) {
            socket.emit('votacio', id);
            switch (id) {
                case 0:
                    this.vots[0]++;
                    break;
                case 1:
                    this.vots[1]++;
                    break;
                case 2:
                    this.vots[2]++
                    break;
                case 3:
                    this.vots[3]++;
                    break;
            }
            pinia.setVotos(this.vots);
 
        },
        async agafarVots(){
            this.vots = await getVotacions();
            pinia.setVotos(this.vots);
        }
    },
    created() {
        this.connectar();
        this.agafarVots();
    },
};

</script>
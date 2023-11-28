<template>
    <v-container>
        <v-row>
            <v-col cols="6">
                <h1>{{ vots1 }}</h1><v-btn @click="this.votar(0)">VOTAR 1</v-btn>
            </v-col>
            <v-col cols="6">
                <h1>{{ vots2 }}</h1><v-btn @click="this.votar(1)">VOTAR 2</v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6">
                <h1>{{ vots3 }}</h1><v-btn @click="this.votar(2)">VOTAR 3</v-btn>
            </v-col>
            <v-col cols="6">
                <h1>{{ vots4 }}</h1><v-btn @click="this.votar(3)">VOTAR 4</v-btn>
            </v-col>

        </v-row>
    </v-container>
</template>

<script>
import { socket, state } from "@/socket";
import { useAppStore } from '@/store/app'
const pinia = useAppStore();

export default {
    name: "Botons",
    data() {
        return {
            vots1: 0,
            vots2: 0,
            vots3: 0,
            vots4: 0

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
                    this.vots1++;
                    break;
                case 1:
                    this.vots2++;
                    break;
                case 2:
                    this.vots3++
                    break;
                case 3:
                    this.vots4++;
                    break;

            }
 
        },
        agafarVots(){
            
        }
    },
    created() {
        this.connectar
        this.agafarVots();
    },
};

</script>
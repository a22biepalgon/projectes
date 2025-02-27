import { reactive } from "vue";
import { io } from "socket.io-client";

export const state = reactive({
  connected: false,
  infoVotos:{
    votos:[]
  }
});

// "undefined" means the URL will be computed from the `window.location` object
const URL = process.env.NODE_ENV === "production" ? undefined : "http://damtr1g3.dam.inspedralbes.cat:3333/";

export const socket = io("http://electronbiel.dam.inspedralbes.cat:3477", {
  withCredentials: true
});

socket.on("connect", () => {
  state.connected = true;
});

socket.on("disconnect", () => {
  state.connected = false;
});
socket.on("actualizacioVotacions", (votos)=>{

})

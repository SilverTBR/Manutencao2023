import { height } from "@fortawesome/free-solid-svg-icons/faBars";
import { StyleSheet } from "react-native";

const estiloVacina = StyleSheet.create({
    imgFundo: {
        width: "90%",
        height: "30%",
        backgroundColor: "white",
        marginTop: 10
    },
    img: {
        width: "100%",
        height: "100%"
    },

    modalContainer: {
        flex: 1, 
        backgroundColor: "rgba(255, 255, 255, 0.75)",
        alignItems: "center",
        justifyContent: "center"
    },

    modalCaixa: {
        backgroundColor: "white",
        width: "90%",
        height: "25%",
        borderWidth: 3,
        borderColor: "#B9DFDB",
        alignItems: "center",
        justifyContent: "center"
    },

    modalBotoes: {
        flexDirection: "row",
        top: 30
    }
})

export  {estiloVacina}
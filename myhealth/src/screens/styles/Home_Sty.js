import { StyleSheet } from "react-native";

const estiloHome = StyleSheet.create ({
    conteudoFlat: {
        flex: 0.5,
        backgroundColor: "#FFFFFF",
        marginVertical: 10,
        marginLeft: 10,
        maxWidth: 180,
        alignItems: "center",
        borderRadius: 5,
    },
    doseText: {
        backgroundColor: "#3F92C5",
        paddingHorizontal: 10,
    },

    tituloFlat: {
        color: "#3F92C5",
        fontSize: 20
    },

    dataText: {
        color: "#8B8B8B",
        fontSize: 12
    },

    flatImage: {
        height: 80,
        width:  165
    },
    
    fontProxVac: {
        color: "#FD7979",
        alignSelf:"flex-end",
        fontSize: 12,
        marginRight: 5
    },


    viewPesquisa: {
        //PORQUE MARGIN RIGHT NUM FUNCIONA??
        width: "90%",
        marginVertical: 20,
        marginLeft: 20,
        height: 36,
        backgroundColor: "white",
        alignItems: "center",
        flexDirection: "row",
        borderRadius: 3
    },

    pesquisaCampo: {
        color: "#8B8B8B",
        fontFamily: "AveriaLibre-Regular",
        backgroundColor: "white",
        flex: 1,
        fontSize: 18,
        borderWidth: 0,
        padding: 0
    }
})

export {estiloHome}
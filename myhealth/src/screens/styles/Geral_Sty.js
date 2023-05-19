import { StyleSheet } from "react-native";

const estilosGeral = StyleSheet.create({
    background: {
        flex: 1,
        backgroundColor: "#ADD4D0",
    },

    conteudoCentro: {
        flex: 90,
        justifyContent: "center",
    },

    //Adcionado para manter os botões não extendidos por toda a view
    conteudoBotao: {
        alignItems: "center"
    },

    textData: {
        color: "#3F92C5",
        textAlign: "center",
        fontFamily: "AveriaLibre-Regular",
        fontSize: 25
    },
    genView:{
        flexDirection: "row",
        alignItems: "center",  
    },

    viewDataCampo: {
        flexDirection: "row",
        height: 40,
        marginTop: 20,
        flexWrap: 'wrap'
    },

    campoData:{
        justifyContent: "space-evenly",
        width: 200,
        flexDirection: "row",
        alignItems: "center",
    },  

    dataIcon: {
        width: 25,
        height: 25,
    },

    viewLabelCampo: {
        flexDirection: "row",
        width: "100%",
        //backgroundColor: "#FFFFFF"
    },

    viewLabel: {
        width: "100%",
        height: 40,
        alignItems: "flex-end",
        justifyContent: "center",
        marginTop: 20,
        marginLeft: 5
    },

    viewLabelList: {
        flex: 30,
    },

    viewCampoList: {
        flex: 70,
        //backgroundColor:"#2596be",
        justifyContent: "flex-start",
        marginLeft: 15
    },
    font: {
        fontFamily: "AveriaLibre-Regular",
    },

    campo: {
        backgroundColor: "#FFFFFF",
        width: '90%',
        height: 40,
        color: "#3F92C5",
        fontFamily: "AveriaLibre-Regular",
        fontSize: 20,
        marginTop: 20
    },

    label: {
        fontSize: 16,
        fontFamily: "AveriaLibre-Regular",
    },

    avisoText: {
        color: "#FD7979",
        fontFamily: "AveriaLibre-Regular",
        fontSize: 15

    }

})

export { estilosGeral }
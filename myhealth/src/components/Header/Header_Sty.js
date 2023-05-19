import { StyleSheet } from "react-native";

const estiloHeader = StyleSheet.create ({
    fundo: {
        flex: 10,
        backgroundColor: "#C1E7E3",
        flexDirection:"row",
        alignItems: "center"
    },
    logo: {
        width: 51,
        height: 51,
        marginHorizontal: 10

    },
    headerText:{
        fontFamily: "AveriaLibre-Regular",
        color: "#419ED7",
        fontSize: 48
    }
})

export {estiloHeader}
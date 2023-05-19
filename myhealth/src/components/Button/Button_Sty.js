import { StyleSheet } from "react-native";

const estilos = (cor, padding, margin) => StyleSheet.create({
    fundo: {
        backgroundColor: cor,
        alignItems: "center",
        justifyContent: "center",
        marginTop: margin ? parseInt(margin): 0,
        shadowColor: "#000000",
        shadowOpacity: 0.8,
        shadowRadius: 2,
        shadowOffset: {
          height: 1,
          width: 1
        },
        elevation: 5,
        paddingHorizontal: 25,
        paddingVertical: padding ? parseInt(padding) : 2,
    },

    text: {
        fontFamily: "AveriaLibre-Regular",
        fontSize: 20,
        fontWeight: 400,
    }
})

export {estilos}
import { StyleSheet, Dimensions } from "react-native";

const estilosInicial = StyleSheet.create ({
    background: {
        flex:1,
    },

    header: {
        flex:30,
        //backgroundColor:'#2596be',
        justifyContent: "space-evenly",
        alignItems: "center",
    },

    body: {
        flex: 40,
        //backgroundColor: '#050505',
        alignItems: "center"
    },

    footer: {
        flex: 30,
        //backgroundColor: '#9e02e6',
        alignItems: "center"
    },

    logo: {
        width: 66,
        height: 58,
    },

    titulo: {
        flexDirection:"row",
        justifyContent: "center",
    },

    tituloText: {
        fontFamily: "AveriaLibre-Bold",
        fontSize: 50,
        color: "rgba(65, 158, 215, 1)",
        alignSelf: "center",
        textDecorationLine: 'underline'
    },

    headerText: {
        fontFamily: "AveriaLibre-Regular",
        fontSize: 33,
        color: "rgba(65, 158, 215, 1)",
        textAlign:'center',
        paddingHorizontal: 5
    },

    label: {
        fontSize: 18,
    },

    imgBackground: {
        width: Dimensions.get('window').width,
        //height: Dimensions.get("window").height,
        height: '100%'
    },

    overlay: {
        ...StyleSheet.absoluteFillObject,
        backgroundColor: "rgba(255, 255, 255, 0.5)"
    },

    labelListInicial: {
        flex: 20
    },

    campoListInicia: {
        flex: 80
    }


})

export {estilosInicial}
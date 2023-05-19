//importação
import { TouchableOpacity, View, Text } from "react-native";
import { estilos } from "./Button_Sty";
//definição
const Button = (props) => {
    return(
        <TouchableOpacity style={estilos(props.cor,props.padding, props.margin).fundo} onPress={props.action}>
            <View>
                <Text style={estilos().text}>{props.texto}</Text>
            </View>
        </TouchableOpacity>
    )
}
//Exportação
export default Button
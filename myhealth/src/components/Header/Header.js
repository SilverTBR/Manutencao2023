import {View,Text,Image, TouchableOpacity} from 'react-native';
import { estiloHeader } from './Header_Sty';
import logo from '../../assets/images/iconvacina.png'

const Header = (props) => {
    return(
        <View style={estiloHeader.fundo}>
            <TouchableOpacity onPress={props.action}>
                <Image style={estiloHeader.logo} source={logo}/>
            </TouchableOpacity>
            <Text style={estiloHeader.headerText}>MyHealth</Text>
        </View>
    )
}

export default Header
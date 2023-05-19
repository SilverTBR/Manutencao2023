import {View,Text,Image, TouchableOpacity} from 'react-native';
import { estiloHeader } from './Header_Sty';
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome'
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft'

const Header = (props) => {


    return(
        <View style={estiloHeader.fundo}>
            <TouchableOpacity onPress={props.action}>
                <FontAwesomeIcon icon={faArrowLeft} size={40} style={{color: "#c0c0c0", marginHorizontal: 10}}/>
            </TouchableOpacity>
            <Text style={estiloHeader.headerText}>MyHealth</Text>
        </View>
    )
}

export default Header
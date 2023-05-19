import {View,Text,Image, TouchableOpacity} from 'react-native';
import { estiloHeader } from '../Header/Header_Sty';
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome'
import { faBars } from '@fortawesome/free-solid-svg-icons/faBars'

const HeaderDrawer = (props) => {
    return(
        <View style={estiloHeader.fundo}>
            <TouchableOpacity onPress={() => {props.navigation.openDrawer()}}>
                <FontAwesomeIcon icon={faBars} size={30} style={{color: "#c0c0c0", marginHorizontal: 10}}/>
                </TouchableOpacity>
            <Text style={estiloHeader.headerText}>MyHealth</Text>
        </View>
    )
}

export default HeaderDrawer
import { View, Text } from 'react-native'
// importação usada como view, mas neste caso se alguma coisa dentro dele passar do tamanho, vai dar a opção de scroll
// Drawer item possiblita colocar outros itens que não sejam telas especificamente
import { DrawerContentScrollView, DrawerItem, DrawerItemList } from '@react-navigation/drawer';

import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome'
import { faArrowRightFromBracket } from '@fortawesome/free-solid-svg-icons/faArrowRightFromBracket'

const CustomDrawer = (props) =>{
    return(
        //Aplica props do drawer pai, no caso no drawer item list poe todas as telas que estão no drawer pai
        // e o drawer item, adciona uma nova
        <DrawerContentScrollView {...props}>
            <View style={{marginVertical: 50, alignItems: "center"}}> 
                <Text style={{color: "#419ED7", fontFamily: "AveriaLibre-Regular", fontSize: 36}}>Olá usuario</Text>
            </View>
            <View style={{backgroundColor: "#419ED7", width: "90%", height: 2, alignSelf: "center"}}></View>
            <DrawerItemList {...props}/>
            <DrawerItem labelStyle={{fontSize: 20, color: "#419ED7", fontFamily: "AveriaLibre-Regular"}} label={"Sair"} onPress={() => props.navigation.popToTop()} icon={() => <FontAwesomeIcon size={30} style={{marginRight:-20}} icon={faArrowRightFromBracket} />}/>
        </DrawerContentScrollView>
    )
}

export default CustomDrawer;
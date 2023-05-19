import { createDrawerNavigator } from "@react-navigation/drawer";
import TelaHome from "../../screens/Home";
import TelaPVacina from "../../screens/PVacina";
import CustomDrawer from "./CustomDrawer";
import { Image } from "react-native";
import logo from '../../assets/images/iconvacina.png'
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome'
import { faCalendarDays } from '@fortawesome/free-solid-svg-icons/faCalendarDays'   


const Drawer = createDrawerNavigator()

const DrawerNavigation = (props) => {
    return(
        <Drawer.Navigator screenOptions={{headerShown: false,drawerLabelStyle: {color:"#419ED7", fontFamily: "AveriaLibre-Regular", fontSize: 20}, drawerActiveBackgroundColor:"#99BAB7", drawerStyle: {backgroundColor: "#ADD4D0"}}} drawerContent={(props) => <CustomDrawer {...props}/>}>
            <Drawer.Screen name="Minhas vacinas" component={TelaHome} options={{drawerIcon: () => <Image style={{width: 30,height: 30, marginRight: -20}} source={logo}/>}}/>
            <Drawer.Screen name="Próximas vacinas" component={TelaPVacina} options={{drawerIcon: () => <FontAwesomeIcon size={30} style={{marginRight: -20}} icon={faCalendarDays} />}}/>
        </Drawer.Navigator>
    )
}

export default DrawerNavigation;
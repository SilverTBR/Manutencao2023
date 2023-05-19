//importações
import { ImageBackground, View, Text, Image, TextInput } from 'react-native'
import { estilosInicial } from './styles/Inicial_Sty';
import { estilosGeral } from './styles/Geral_Sty';
import Button from '../components/Button/Button'
import logo from '../assets/images/iconvacina.png'
import background from '../assets/images/background.jpg'



//definição de componente
const App = (props) => {

    const stackTela = (tela) => {
        props.navigation.push(tela);
    }

    return (
        <View style={estilosInicial.background}>
            <ImageBackground source={background} resizeMode='cover' style={estilosInicial.imgBackground}>
                <View style={estilosInicial.overlay}></View>
                <View style={estilosInicial.header}>
                    <View style={estilosInicial.titulo}>
                        <Image style={estilosInicial.logo} source={logo} />
                        <Text style={estilosInicial.tituloText}>MyHealth</Text>
                    </View>
                    <Text style={estilosInicial.headerText}>Controle as suas vacinas e fique seguro</Text>
                </View>
                <View style={estilosInicial.body}>
                    <View style={estilosGeral.conteudoCentro}>
                        <View style={estilosGeral.viewLabelCampo}>
                            <View style={estilosInicial.labelListInicial}>
                                <View style={estilosGeral.viewLabel}>
                                    <Text style={[estilosGeral.label, estilosInicial.label]}>E-mail</Text>
                                </View>
                                <View style={estilosGeral.viewLabel}>
                                    <Text style={[estilosGeral.label, estilosInicial.label]}>Senha</Text>
                                </View>
                            </View>
                            <View style={[estilosGeral.viewCampoList, estilosInicial.campoListInicia]}>
                                <TextInput style={estilosGeral.campo}></TextInput>
                                <TextInput style={estilosGeral.campo} secureTextEntry={true}></TextInput>
                                <Text style={estilosGeral.avisoText}>E-mail e/ou senha inválidos.</Text>
                            </View>
                        </View>
                        <View style={estilosGeral.conteudoBotao}>
                            <Button cor="#37BD6D" texto="Entrar" width="250" margin="60" padding="8" action={() => stackTela("DrawerNavigation")} />
                        </View>
                    </View>
                </View>
                <View style={estilosInicial.footer}>
                    <Button texto="Criar minha conta" cor="#419ED7" padding="8" margin="70" action={() => stackTela("CadUsuario")} />
                    <Button texto="Esqueci minha conta" cor="#B0CCDE" margin="50" action={() => stackTela("RecSenha")} />
                </View>
            </ImageBackground>
        </View>
    );
}



//exportação
export default App
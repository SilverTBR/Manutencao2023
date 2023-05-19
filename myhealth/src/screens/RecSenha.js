import {View,Text,TextInput} from 'react-native';
import { estilosGeral } from './styles/Geral_Sty';
import Header from '../components/Header/Header'
import Button from '../components/Button/Button';

const RecSenha = (props) => {
    const stackReturn = () =>{
        props.navigation.popToTop()
    }

    const stackTela = (tela) =>{
        props.navigation.push(tela);
    }

    return(
        <View style={estilosGeral.background}>
            <Header action={() => stackReturn("RecSenha")}/>
            <View style={estilosGeral.conteudoCentro}>
                <View style={estilosGeral.viewLabelCampo}>
                    <View style={[estilosGeral.viewLabelList, {flex: 15}]}>
                        <View style={estilosGeral.viewLabel}>
                            <Text style={estilosGeral.label}>E-mail</Text>
                        </View>
                    </View>
                    <View style={estilosGeral.viewCampoList}>
                        <TextInput style={estilosGeral.campo}></TextInput>  
                    </View>
                </View>
                <View style={estilosGeral.conteudoBotao}>    
                    <Button cor="#37BD6D" texto = "Recuperar senha" width="250" margin = "100" action={() => stackTela("TelaHome")}/>
                </View>
            </View>
        </View>
    )
}

export default RecSenha;
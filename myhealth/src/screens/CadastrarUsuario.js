import {TouchableOpacity, View, Text, Image, TextInput} from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import {useState} from 'react';
import {estilosGeral} from './styles/Geral_Sty';
import Header from '../components/Header/Header'
import Button from '../components/Button/Button';
import logo from '../assets/images/iconcalendario.png';
import { RadioButton } from 'react-native-paper';


const CadUsuario = (props) => {
    const [date, setDate] = useState(new Date());
    const [show, setShow] = useState(false);
    const [genero, setGenero] = useState('M');

    const stackReturn = () => {
        props.navigation.popToTop()
    }

    const stackTela = (tela) => {
        props.navigation.push(tela);
    }

    const onChange = (event, selectedDate) => {
        const currentDate = selectedDate || date;
        setShow(false);
        setDate(currentDate);
        //console.log('Data selecionada:', date.toLocaleDateString());
    };

    const showDatepicker = () => {
        setShow(true);
    };

    return (
        <View style={estilosGeral.background}>
            <Header action={() => stackReturn("RecSenha")} />
            <View style={estilosGeral.conteudoCentro}>
                <View style={estilosGeral.viewLabelCampo}>

                    <View style={estilosGeral.viewLabelList}>
                        <View style={estilosGeral.viewLabel}>
                            <Text style={estilosGeral.label}>Nome Completo</Text>
                        </View>
                        <View style={estilosGeral.viewLabel}>
                            <Text style={estilosGeral.label}>Sexo</Text>
                        </View>
                        <View style={estilosGeral.viewLabel}>
                            <Text style={estilosGeral.label}>Data Nascimento</Text>
                        </View>
                        <View style={estilosGeral.viewLabel}>
                            <Text style={estilosGeral.label}>E-mail</Text>
                        </View>
                        <View style={estilosGeral.viewLabel}>
                            <Text style={estilosGeral.label}>Senha</Text>
                        </View>
                        <View style={estilosGeral.viewLabel}>
                            <Text style={estilosGeral.label}>Repetir Senha</Text>
                        </View>
                    </View>

                    <View style={estilosGeral.viewCampoList}>
                        {/* Campos de inserção */}
                        <TextInput style={estilosGeral.campo}></TextInput>

                        <RadioButton.Group onValueChange={genero => setGenero(genero)} value={genero}>
                            <View style={[estilosGeral.viewDataCampo]}>
                            <View style={estilosGeral.genView}>
                                <RadioButton value='M' color='#419ED7'/>
                                <Text style={estilosGeral.label}>Masculino</Text>

                            </View>
                            <View style={estilosGeral.genView}>
                                <RadioButton value='F' color='#419ED7'/>
                                <Text style={estilosGeral.label}>Feminino</Text>

                            </View>
                            </View>
                        </RadioButton.Group>

                        <TouchableOpacity onPress={showDatepicker} style={[estilosGeral.campo, estilosGeral.campoData]}>
                            <Text style={estilosGeral.textData}>{date.toLocaleDateString()}</Text>
                            <Image source={logo} style={estilosGeral.dataIcon} />
                        </TouchableOpacity>
                        {show && (<DateTimePicker testID='dateTimePicker' value={date} mode='date' display='default' onChange={onChange} />)}

                        <TextInput style={estilosGeral.campo}></TextInput>

                        <TextInput style={estilosGeral.campo} secureTextEntry={true} ></TextInput>

                        <TextInput style={estilosGeral.campo} secureTextEntry={true} ></TextInput>
                        <Text style={estilosGeral.avisoText}>Senha não confere!</Text>

                    </View>


                </View>
                <View style={estilosGeral.conteudoBotao}>
                    <Button texto="Cadastrar" cor="#37BD6D" margin="50" padding="6" action={() => stackTela("TelaHome")} />
                </View>

            </View>
        </View>
    )
}

export default CadUsuario;
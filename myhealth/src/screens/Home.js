import { FlatList, View, Text, Image, TextInput } from 'react-native'
import { estilosGeral } from './styles/Geral_Sty';
import Header from '../components/Drawer/HeaderDrawer';
import { funcVacinas } from '../controller/Vacinas';
import { useState, useEffect } from 'react';
import { estiloHome } from './styles/Home_Sty';
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome'
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons/faMagnifyingGlass'
import Button from '../components/Button/Button'
import { TouchableOpacity } from 'react-native-gesture-handler';

const TelaHome = (props) => {
  const [pesquisa, setPesquisa] = useState("");
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    props.navigation.addListener('focus', () => {
      setRefresh(!refresh);
    });
    });



  const stackTela = (tela, vacina) => {
    props.navigation.push(tela, {vacina: vacina});
  }

  let numColumns = 2


  const renderItem = ({ item: vacina }) => {
    return (
      <View style={estiloHome.conteudoFlat}>
      <TouchableOpacity style = {{alignItems: "center"}} onPress={() => stackTela("NovaVacina", vacina)}>
          <Text style={[estilosGeral.font, estiloHome.tituloFlat]}>{vacina.nome}</Text>
          <Text style={[estilosGeral.font, estiloHome.doseText]}>{funcVacinas.tipoDose(vacina.dose)}</Text>
          <Text style={[estilosGeral.font, estiloHome.dataText]}>{vacina.dataVac}</Text>
          <Image source={{ uri: vacina.imagem }} style={estiloHome.flatImage} />
          <Text style={[estiloHome.fontProxVac, estilosGeral.font]}>{funcVacinas.verData(vacina.dataProxV)}</Text>
      </TouchableOpacity>
      </View>
    );
  };

  return (



    <View style={[estilosGeral.background]}>
      <Header {...props} />

      <View style={estilosGeral.conteudoCentro}>

        <View style={estiloHome.viewPesquisa}>
          <FontAwesomeIcon icon={faMagnifyingGlass} size={30} style={{color: "#c0c0c0", marginHorizontal: 10}}/>
          <TextInput placeholder="PESQUISAR VACINA..." placeholderTextColor={"#8B8B8B"} style={estiloHome.pesquisaCampo} value={pesquisa} onChangeText={setPesquisa}></TextInput>
        </View>

        <View style={{justifyContent: "center", flex: 90, }}>
          <FlatList
            data={funcVacinas.returnVacinas()}
            renderItem={renderItem}
            keyExtractor={(item) => item.id}
            numColumns={numColumns}
            extraData={refresh}
            
          />
        </View>
        <View style={[estilosGeral.conteudoBotao, {marginBottom: 50}]}>    
            <Button cor="#37BD6D" texto = "Nova vacina" padding="5"  action={() => stackTela("NovaVacina")}/>
        </View>

      </View>
    </View>
  )
}

export default TelaHome;

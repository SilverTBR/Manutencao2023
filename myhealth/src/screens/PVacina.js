import { View, Text, FlatList } from 'react-native'
import { estilosGeral } from './styles/Geral_Sty';
import { estiloProx } from './styles/ProxV_Sty';
import Header from '../components/Drawer/HeaderDrawer';
import { funcVacinas } from '../controller/Vacinas';
import Button from '../components/Button/Button';
import { useEffect, useState } from 'react';


const TelaPVacina = (props) => {
    const [refresh, setRefresh] = useState(false);

    const stackTela = (tela, vacina) => {
        props.navigation.push(tela, {vacina: vacina});
      }

      useEffect(() => {
        props.navigation.addListener('focus', () => {
          setRefresh(!refresh);
        });
      });
    


    const renderItem = ({ item: vacina }) => {
        return (
          <View style={estiloProx.conteudoFlat}>
              <Text style={[estilosGeral.font, estiloProx.tituloFlat]}>{vacina.nome}</Text>
              <Text style={[estiloProx.fontProxVac, estilosGeral.font]}>{vacina.dataProxV}</Text>
          </View>
      
    
        );
      };

    return (
    <View style={[estilosGeral.background]}>
      <Header {...props} />
      <View style={estilosGeral.conteudoCentro}>
        <View style={{justifyContent: "center", flex: 90, }}>
          <FlatList
            data={funcVacinas.returnVacinasProx()}
            renderItem={renderItem}
            keyExtractor={(item) => item.id}
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

export default TelaPVacina;

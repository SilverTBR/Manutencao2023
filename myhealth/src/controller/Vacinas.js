const vacinas = [
    {id: 0, nome: "Tetano", dataVac: "25/04/2010", dataProxV: 0, dose: 4, imagem: "https://i.imgur.com/RYNFZRm.png"},
    {id: 1, nome: "BCG", dataVac: "22/03/2000", dose: 2, dataProxV: "30/12/2018", imagem: "https://i.imgur.com/RYNFZRm.png"},
    {id: 2, nome: "Dengue", dataVac: "18/02/2019", dose: 3, dataProxV: "18/05/2019", imagem: "https://i.imgur.com/RYNFZRm.png"},
    {id: 3, nome: "Rotavírus", dataVac: "01/08/2010", dose: 4, dataProxV: 0, imagem: "https://i.imgur.com/RYNFZRm.png"},
    {id: 4, nome: "Poliomielite oral", dataVac: "12/12/2012", dose: 1, dataProxV: "07/04/2030", imagem: "https://i.imgur.com/RYNFZRm.png"}


]

const pushVacinas = (vacina) =>{
    vacina.id = vacinas[vacinas.length-1].id+1;
    vacinas.push(vacina);
    console.log(vacina)
}

const returnVacinas = () => {
    return vacinas;
} 

const returnVacinasID = (id) => {
    let resultado = vacinas.find(vacina => vacina.id === id);
    if(resultado === undefined){
        return -1
    }else{
        return resultado;
    }
}

const excluirID = (id) => {
    index = vacinas.findIndex(tempVac => tempVac.id === id)
    vacinas.splice(index,1);
}

const returnVacinasProx = () => {
    const vacinasProx = vacinas.filter(vacina => vacina.dose !== 4);
    return vacinasProx;   
}

const returnVacinasIndex = (id) => {
    let resultado = vacinas.findIndex(vacina => vacina.id === id);
    return resultado
}

const tipoDose = (valTip) => {
    switch (valTip) {
        case 1:
            return "1ª dose"
        case 2:
            return "2ª dose"
        case 3:
            return "3ª dose"
        case 4:
            return "Dose única"
    }
}

const verData = (data) => {
    if(data === 0){
        return "Não há próxima dose"
    }else{
        return "Próxima dose em: "+data
    }
}

const editar = (vacina) => {
   let index = returnVacinasIndex(vacina.id);
   vacinas[index] = vacina;
}

export const funcVacinas =
{pushVacinas, returnVacinas, returnVacinasID, tipoDose, verData, editar, returnVacinasProx, excluirID};
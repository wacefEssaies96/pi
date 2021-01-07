import * as React from 'react';
// import {Input} from 'react-native-elements';
// import Icon from 'react-native-vector-icons/FontAwesome';
import { View,FlatList,StyleSheet,Image } from 'react-native';
import axios from "axios";
import  { getAuthData } from './Auth';

export default class ProductList extends React.Component{
  state = {
    list: [],
    filter: ''
  }

  

  componentDidMount() {

  }
  constructor(props) {
    super(props)
    this.getData().then(response => {
      this.setState({list:response.data})
      return this.list;
    });
  }
  //search
  // searchProductRequest(props) {
  //   let headers = {'Content-Type': 'text/json'};
  //   let data = getAuthData()
  //   //data.filter = this.state.filter;
  //   alert(JSON.stringify(this.props));
  //   return;
  //   axios.post('http://localhost:8000/api/product', data, {headers:headers}).then(response => {
  //     this.setState({list:response.data})
  //       return this.list;
  //   })
  // }


  // searchProduct() {
  //     this.searchProductRequest().then(response => {
  //       this.setState({list:response.data})
  //       return this.list;
  //     });
      
  //   }
  async getData() {
    let data = getAuthData()
    let headers = {'Content-Type': 'text/json'};
    return await axios.post('http://localhost:8000/api/list', data, {headers:headers})
  }
  
  renderProduct(item) {
    let product = item.item;
    return(

     <View>
      <p>{product.name + '||' + product.list_price + '$' }</p>
      <Image  
             source={'data:image/jpeg;base64,'+product.image}
             style={{width:50,height:50}}>
             </Image>
      </View>
      
      
    )
  }


  render(){
    return (
   


      <View style={this.styles.container}>
        <h1>Product list</h1>
        
        {/* search */}
        {/* <Input
            placeholder="Rechercher ..."
            leftIcon= {
                <Icon
                    name = "search"
                    size={22}
                    color='black'
                />
            }
            onChangeText= {text =>{this.state.filter=text;}}
        />
        <Button onPress= {this.searchProductRequest}  title="Rechercher" color="#0148a4"/> */}
      
        
        <FlatList
          data={this.state.list}
          renderItem={this.renderProduct}
          keyExtractor={item => item.id}
        />
        
      </View>
      );
    }
    styles = StyleSheet.create({
      container: {
       flex: 1,
       paddingTop: 22
      },
      sectionHeader: {
        paddingTop: 2,
        paddingLeft: 10,
        paddingRight: 10,
        paddingBottom: 2,
        fontSize: 14,
        fontWeight: 'bold',
        backgroundColor: 'rgba(247,247,247,1.0)',
      },
      item: {
        padding: 10,
        fontSize: 18,
        height: 44,
      },
      
    })
  
  
}

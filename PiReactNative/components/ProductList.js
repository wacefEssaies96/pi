import * as React from 'react';
import {Text, View,FlatList, Form,StyleSheet, SafeAreaView } from 'react-native';
import axios from "axios";
export default class ProductList extends React.Component{
  state = {
    list: []
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

  async getData() {
    return await axios.get('http://localhost:8000/api/list')
  }
  
  renderProduct(item) {
    let product = item.item;
    return <p>{product.name + '||' + product.list_price + '$' + '||' + "data:image/jpeg;base64,"+product.image }</p>
  }

  render(){
  return (
    <View style={this.styles.container}>
      <h1>Product list</h1>
      <FlatList
        data={this.state.list}
        renderItem={this.renderProduct}
        keyExtractor={item => item.id}
      >
      </FlatList>
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

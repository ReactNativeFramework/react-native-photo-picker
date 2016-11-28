/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
    View,
  StyleSheet,
    Button,
    ListView,
    Image
} from 'react-native';


import PhotoPickerAndroid from 'react-native-photo-picker';

export default class demo extends Component {
// 构造
  constructor(props) {
    super(props);
    this._renderRow = this._renderRow.bind(this);
    this.ds = new ListView.DataSource({
      rowHasChanged:(r1,r2)=>r1 !== r2
    });
    // 初始状态
    this.state = {
      dataSource:this.ds.cloneWithRows([])
    };
  }
  _renderRow(rowData,sectionId:number,rowId:number){
    return(
       <Image style={{width:150,height:150,margin:10,borderWidth:1,borderColor:'gray',borderRadius:10}} source={{uri:"file://"+rowData}} />

    );
  }
  render() {
    return (
      <View style={styles.container}>
        <Button
          onPress={()=>{PhotoPickerAndroid.photoPicker(null,(paths)=>{
            this.setState({
                dataSource:this.ds.cloneWithRows(paths)
            })
          })}}
          title='选择图片' />
        <ListView
            dataSource={this.state.dataSource}
            renderRow={this._renderRow}
            enableEmptySections={true}
            initialListSize= {4}
            contentContainerStyle={{justifyContent:'space-between', flexDirection:'row',flexWrap:'wrap'}}
            />

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('demo', () => demo);

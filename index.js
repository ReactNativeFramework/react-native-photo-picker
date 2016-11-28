import {
    NativeModules
    } from 'react-native';

const {PhotoPickerModel:PhotoPickerAndroid} = NativeModules;
export default {
    photoPicker(options,callback){

        PhotoPickerAndroid.photoPicker(options,callback);
    }
}
import  React from 'react';
import {Text,StyleSheet,TouchableOpacity} from 'react-native';
import {Button} from 'react-native-elements';



const Submit = props =>
{
    return(
    <Button>
        <TouchableOpacity style={[styles.container, {backgroundColor: props.Color}]}>
            <Text style={styles.submitText}>{props.title}</Text>

        </TouchableOpacity>
        
    </Button>
    );
};


const styles = StyleSheet.create({
    container: {
        width: '90%',
        height: 50,
        borderColor: 'blue',
        borderRadius: 10,
        marginVertical:10,
        borderWidth:0,
    },
    submitText: {
        fontSize: 22,
        fontWeight:'bold',
        color:'white',
        alignSelf: 'center',
        marginVertical:10,
    }
}); 

export default Submit;
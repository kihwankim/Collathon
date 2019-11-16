import React, { Component } from "react"
import axios from "axios"
import { StyleSheet,Vibration, Text, View, Button, AppRegistry } from "react-native"


class AdminScreen extends Component {
    constructor(props){
        super(props);
    }
    render(){
        return (
            <View>
                <Button
                    title="Start"/>
            </View>
        );
    }
}

export default AdminScreen;
import React from "react";
import { View, Button, StyleSheet } from "react-native";
import { NativeModules } from "react-native";

const { DoNotDisturbModule } = NativeModules;

const StartScreen = ({ navigation }) => {
    const handleDND = () => {
        console.log("We will invoke the DoNotDisturb module here!");
        DoNotDisturbModule.enableDoNotDisturb();
        navigation.navigate("Home");
    };
    return (
        <View style={styles.container}>
            <Button
                title="Enable Do Not Disturb to start"
                color="#841584"
                onPress={handleDND}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
});

export default StartScreen;

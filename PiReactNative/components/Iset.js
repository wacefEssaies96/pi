import React from 'react';
import {View,Text,StyleSheet, Image} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';


const Iset=()=>
{
  
    return(
      
        <View style={styles.row}>
         
            <Image  
            source={require('../assets/iset.jpg')}
            style={styles.imageContainer}>
            </Image>
            <View style={styles.textContainer}>

              <View style={styles.row}>
                <Text style={styles.titleContainer}>
                  <div>Institut Supérieur des études Technologiques de Bizerte </div>
                  <div>المعهد العالي للدراسات التكنولوجية ببنزرت</div>
                </Text>
                <Text style={styles.versionContainer}>
                
                </Text>

              </View>

              <Text style={styles.descriptionContainer}>
                <div>Description du ISETB :</div>
<div>ISET de Bizerte est un établissement public à caractère scientifique et technologique. Sa principale mission consiste à contribuer au développement socio-économique et au transfert technologique dans la région Bizerte ainsi que le renforcement de l'employabilité l’insertion professionnelle des diplômés de l’enseignement supérieur.</div>
<div>Composants du bâtiment :</div>
<div>Les édifices de l’ISET de Bizerte se composent de quatre blocs d’une superficie dépassant les 13 000m².
Bloc N°1 : Hall principal et entrée des étudiants et les invités de l’institut
Ce premier bloc comprend entre autre :</div>
 <div>Un amphi théâtre d’une capacité de 300 places destiné à l’enseignement ou toute autre manifestation (scientifiques ou autres).
 Une cinquantaine de salles de cours d’une capacité moyenne de trente étudiants.
 15 labos informatiques
 13 labos génie électrique
 9 labos génie de procédés
 2 Laboratoires de génie mécanique
 Laboratoire de langue
 Bibliothèque
 Salle de lecture
 Salle de révision</div>
<div>Bloc N°2 : Administration</div>
 <div>Administration
 Scolarité
 Une salle de réunions</div>
<div>Bloc N°3 : Bureaux et locaux divers</div>
 <div>Bureaux pour enseignants
 Locaux pour les clubs et les activités socio- culturelle des étudiants
 Terrains pour les activités sportives
 Librairie et photocopie
 Buvette</div>
<div>Bloc N°4 : Bloc pépinière d’entreprise</div>
 <div>Superficie ; 2500 m²
 14 bureaux
 Grand hall d'exposition </div>
              </Text>
              <Text style={styles.dateContainer}>
                 
              </Text>

            </View>

        </View>
      
    );
}


const styles = StyleSheet.create({
  
    row: {
      flex: 1,
      flexDirection: "row",
    },

    imageContainer:{
      flex:1,
       width: 100,
       height: 100,
       resizeMode: 'contain' 
    },
    textContainer:{
        flex :9,
    },

    titleContainer:{
      flex:3,
      
    },
    versionContainer:{
      flex:2,
      textAlign: "right",
    },
    descriptionContainer:{
      flex:4,
    },
    dateContainer:{
      flex:0.5,
      textAlign: "right",
    },

  });
export default  Iset;
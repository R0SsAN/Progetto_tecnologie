//PROGRAMMA ARDUINO CENTRALINA_PRINCIPALE
#include <SoftwareSerial.h>
//definizione pin relè
#define btn1 6
#define btn2 5
#define btn3 4
#define btnTermo 3
#define btnLuci 2
//definizione pin sensore luminosita
#define sensLum A0
//definizione pin led di stato
#define ledStato 7
//definizione seriale virtuale per arduino sicurezza
#define SOFTRX 13
#define SOFTTX 12
SoftwareSerial serial2(SOFTRX, SOFTTX);
//definizione variabili ricevute da java
int bottone1;
int bottone2;
int bottone3;
int termostato;
int luce;
int allarme;
int valoreLuce;
int valoreTemperatura;
//definizione variabili ricevute da arduino sicurezza
int distanza1;
int distanza2;

void setup() {
  //setup comunicazioni seriali
  Serial.begin(9600);
  serial2.begin(9600);
  //setup pin relè
  pinMode(btn1,OUTPUT);
  pinMode(btn2,OUTPUT);
  pinMode(btn3,OUTPUT);
  pinMode(btnTermo,OUTPUT);
  pinMode(btnLuci,OUTPUT);
  //setup pin sensore luminos
  pinMode(sensLum,INPUT);
  //setup pin led di stato
  pinMode(ledStato,OUTPUT);
  digitalWrite(ledStato,LOW);
  //inizializzazione variabili
  inizializzazioneVariabili();
}

void loop() {
  //accendo il led di stato
  digitalWrite(ledStato,HIGH);
  //leggo le seriali da arduino sicurezza e da java
  leggiSerialeSicurezza();
  leggiSerialeJava();
  //invio informazioni a arduino sicurezza
  invioSerialeSicurezza();
  //gestisco accensione e spegnimento rele
  controlloRele();
  //invio informazioni java
  invioSerialeJava();
  delay(50);

}
void controlloRele()
{
  if(bottone1)
    digitalWrite(btn1,HIGH);
  else
    digitalWrite(btn1,LOW);
  if(bottone2)
    digitalWrite(btn2,HIGH);
  else
    digitalWrite(btn2,LOW);
  if(bottone3)
    digitalWrite(btn3,HIGH);
  else
    digitalWrite(btn3,LOW);
  if(termostato)
    digitalWrite(btnTermo,HIGH);
  else
    digitalWrite(btnTermo,LOW);
  if(luce)
    digitalWrite(btnLuci,HIGH);
  else
    digitalWrite(btnLuci,LOW);
    
}

void inizializzazioneVariabili()
{
  bottone1=0;
  bottone2=0;
  bottone3=0;
  termostato=0;
  luce=0;
  allarme=0;
  valoreLuce=0;
  valoreTemperatura=30;
  distanza1=999;
  distanza2=999;
  
}
void invioSerialeSicurezza()
{
  serial2.print(allarme+";");
}
void invioSerialeJava()
{
  String invio=valoreLuce+"-"+valoreTemperatura+"-"+distanza1+"-"+distanza2+";";
  Serial.print(invio);
}
void leggiSerialeJava()
{
  bottone1=leggiFino('-');
  bottone2=leggiFino('-');
  bottone3=leggiFino('-');
  termostato=leggiFino('-');
  luce=leggiFino('-');
  allarme=leggiFino(';');
}
void leggiSerialeSicurezza()
{
  distanza1=leggiFino('-');
  distanza2=leggiFino(';');
}
int leggiFino(char terminatore)
{
  String temp="";
  //continuo a leggere dalla seriale fino a quando non leggo il carattere terminatore
  while(true)
  {
    if(Serial.available()>0)
    {
      char c=Serial.read();
      if(c==terminatore)
        return temp.toInt();
      else
        temp+=c;
    }
  }
}

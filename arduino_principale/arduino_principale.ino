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
//definizione pin sensore temperatura
#define sensTemp A1
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
  //setup pin sensore temperatura
  pinMode(sensTemp,INPUT);
  //setup pin led di stato
  pinMode(ledStato,OUTPUT);
  digitalWrite(ledStato,LOW);
  //inizializzazione variabili
  inizializzazioneVariabili();
}

void loop() {
  //accendo il led di stato
  digitalWrite(ledStato,HIGH);
  //leggo le seriali da java
  leggiSerialeJava();
  //invio informazioni a arduino sicurezza
  invioSerialeSicurezza();
  //leggo informazioni da arduino sicurezza
  leggiSerialeSicurezza();
  //gestisco accensione e spegnimento rele
  controlloRele();
  //leggo valori sensori temperatura e luminosità
  monitoraSensori();
  //invio informazioni java
  invioSerialeJava();
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
void monitoraSensori()
{
  //calcolo temperatura
  int tempReading = analogRead(sensTemp);
  double tempK = log(10000.0 * ((1024.0 / tempReading - 1)));
  tempK = 1 / (0.001129148 + (0.000234125 + (0.0000000876741 * tempK * tempK ))
  * tempK );
  valoreTemperatura = (int)(tempK - 273.15);
  //calcolo luminosita
  valoreLuce = analogRead(sensLum);
  
}

void inizializzazioneVariabili()
{
  bottone1=0;
  bottone2=0;
  bottone3=0;
  termostato=0;
  luce=0;
  allarme=0;
  valoreLuce=200;
  valoreTemperatura=30;
  distanza1=999;
  distanza2=999;
  
}
void invioSerialeSicurezza()
{
  serial2.print(String(allarme)+";");
}
void invioSerialeJava()
{
  String invio=String(valoreLuce)+"-"+String(valoreTemperatura)+"-"+String(distanza1)+"-"+String(distanza2)+";";
  Serial.println(invio);
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
  distanza1=leggiFino2('-');
  distanza2=leggiFino2(';');
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
      {
        Serial.print(temp+"-");
        return temp.toInt();
      }
        
      else
        temp+=c;
    }
  }
}
int leggiFino2(char terminatore)
{
  String temp="";
  //continuo a leggere dalla seriale fino a quando non leggo il carattere terminatore
  while(true)
  {
    if(serial2.available()>0)
    {
      char c=serial2.read();
      if(c==terminatore)
      {
        return temp.toInt();
      }
        
      else
        temp+=c;
    }
  }
}

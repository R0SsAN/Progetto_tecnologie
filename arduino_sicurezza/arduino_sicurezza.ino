#define echo1 4
#define trigger1 3
#define echo2 6
#define trigger2 5
#define buzzer 2
int distanza1 = 0;
int distanza2 = 0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(trigger1, OUTPUT);
  pinMode(trigger2, OUTPUT);
  pinMode(echo1, INPUT);
  pinMode(echo2, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  int suonaBuzzer = leggiFino(';');
  if (suonaBuzzer == 1){
    tone(buzzer, 660);
  } else if (suonaBuzzer == 0){
    noTone(buzzer);
  }
  calcolaDistanza();
  String s=String(distanza1) + "-" + String(distanza2) + ";";
  Serial.print(s);
}
void calcolaDistanza()
{
  long durata;
  digitalWrite(trigger1,LOW);
  digitalWrite(trigger1,HIGH);
  delayMicroseconds(10);
  digitalWrite(trigger1,LOW);
  durata=pulseIn(echo1,HIGH);
  distanza1=(int)durata/58.31;
  digitalWrite(trigger2,LOW);
  digitalWrite(trigger2,HIGH);
  delayMicroseconds(10);
  digitalWrite(trigger2,LOW);
  durata=pulseIn(echo2,HIGH);
  distanza2=(int)durata/58.31;
}
int leggiFino(char terminatore)
{
  String temp="";
  while(true)
  {
    if(Serial.available()>0)
    {
      char c=(char)Serial.read();
      if(c==terminatore)
        return temp.toInt();
      else
        temp+=c;
    }
  }
}

console.log("js funziona");
var app = new Vue({
    el: "#container-vue",
    data: {
        bottoni: [
            false,
            false,
            false,
            false,
            false,
            false
        ],
        scene: [
            false,
            false,
            false
        ],

        mod_btn: "acceso"
    },
    mounted() {
        console.log("vue funziona");
    },
    mounted: function() {
        this.interval = setInterval(() => this.aggiorna_bottoni(), 2000);
    },
    methods: {
        ricevi_variabili(){
            axios.get("http://194.87.139.81/api/updates.php",
            {
                params: {
                    action: "get_update"
                }
            })
            .then(function (risultato) {
                app.bottoni=risultato.data.bottoni;
                app.scene=risultato.data.scene;
            });
        },
        aggiorna_variabili(i) {
            console.log("chiamata axios");
            axios.get("http://194.87.139.81/api/updates.php",
                {
                    params: {
                        action: "set_bottoni",
                        data: btoa(i+"|" + (this.bottoni[i]).toString())
                    }
                })
                .then();
        },
        aggiornaBottone(i) {
            if(this.bottoni[i]==true)
                this.bottoni[i]=false;
            else
                this.bottoni[i]=true;
            this.aggiorna_variabili(i);
        },
        btnPremuto(i){
            if(i>2)
            {
                if(this.scene[i-3]==false)
                    alert("Scena disattivata, non è possibile utilizzarla!");
                else
                {
                    if(document.getElementsByClassName("btn-bg")[i].classList.length==1)
                        document.getElementsByClassName("btn-bg")[i].classList.add("active");
                    else
                        document.getElementsByClassName("btn-bg")[i].classList.remove("active");

                    this.aggiornaBottone(i);
                }
            }
            else
            {
                if(document.getElementsByClassName("btn-bg")[i].classList.length==1)
                    document.getElementsByClassName("btn-bg")[i].classList.add("active");
                else
                    document.getElementsByClassName("btn-bg")[i].classList.remove("active");

                this.aggiornaBottone(i);
            }
            
        },
        aggiorna_bottoni(){
            this.ricevi_variabili();
            console.log(this.bottoni);
            for(var i=0;i<this.bottoni.length;i++)
            {
                if(this.bottoni[i]==true)
                    document.getElementsByClassName("btn-bg")[i].classList.add("active");
                else
                {
                    if(document.getElementsByClassName("btn-bg")[i].classList.length==2)
                        document.getElementsByClassName("btn-bg")[i].classList.remove("active");
                }
            }
        }

        



    }
})
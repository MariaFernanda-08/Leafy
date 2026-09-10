require("dotenv").config()

const express = require("express")
const mysql = require("mysql2")
const cors =  require("cors")

const app = express()

app.use(cors())
app.use(express.json())

// CONEXÃO 
const connection = mysql.createConnection({ // configuração da conexão com o banco
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    port: process.env.DB_PORT,
})

connection.connect((error) => { // testar conexão c o MySQL
    if (error) {
        console.error("Erro ao conectar ao MySQL:", error)
        return;
    }

    console.log("Conectado ao banco de dados.")
})

function formatarResiduo(residuo){ // formata dados do banco para API
    return{
        ...residuo,
        reciclavel: Boolean(residuo.reciclavel)
    }
}

// GET
app.get("/", (req,res) => { // teste da API
    res.json({
        mensagem: "API Leafy funcionando!"
    })
})

app.get("/residuos", (req,res) => {
    const sql = "SELECT * FROM residuos";
    connection.query(sql,(error, results) => {
        if (error) {
            console.error("Erro ao buscar resíduos:", error);
            return res.status(500).json({
                erro: "Erro ao buscar resíduos"
            })
        }
        const residuosFormatados = results.map(formatarResiduo)
        res.json(residuosFormatados)
    })
})

app.get("/residuos/buscar",(req,res)=> { // vai pesquisar o resíduo pelo nome
    const nome = req.query.nome;

    if(!nome){
        return res.status(400).json({
            erro: "Informe um nome para pesquisar"
        })
    }

    const sql = "SELECT * FROM residuos WHERE nome LIKE ?"

    connection.query(
        sql,
        [`%${nome}%`],
        (error, results) => {
            if (error) {
                console.error("Erro ao pesquisar resíduos:", error)
                return res.status(500).json({
                    erro: "Erro ao pesquisar resíudos"
                })
            }
            const residuosFormatados = results.map(formatarResiduo)
            res.json(residuosFormatados)
        }
    )
})

app.get("/residuos/codigo/:codigo", (req,res) => { // vai buscar pelo código de barras
    const codigo = req.params.codigo
    const sql = "SELECT * FROM residuos WHERE codigo_barras = ?"
    
    connection.query(sql, [codigo], (error, results) => {
        if (error) {
            console.error("Erro ao buscar resíduo pelo código:", error)

            return res.status(500).json({
                erro: "Erro ao buscar resíduo"
            })
        }
        if (results.length === 0){
            return res.status(404).json({
                erro: "Produto não encontrado"
            })
        }
        res.json(formatarResiduo(results[0]))
    })

})

app.get("/residuos/:id", (req,res) => { // vai buscar o residuo pelo ID
    const id = req.params.id
    const sql = "SELECT * FROM residuos WHERE id = ?"
    connection.query(sql, [id], (error, results) => {
        if (error) {
            console.error("Erro ao buscar resíduo:", error)
            return res.status(500).json({
                erro: "Erro ao buscar resíduo"
            })
        }
        if(results.length === 0){ //ve se teve algum resultado
            return res.status(404).json({
                erro: "Resíduo não encontrado"
            })
        }
        res.json(formatarResiduo(results[0]))
    })
})

app.get("/pontos-coleta", (req,res) => { //ve os pontos de coleta
    const sql = "SELECT * FROM pontos_coleta"

    connection.query(sql, (error, resultados) => {
        if (error){
            console.error("Erro ao buscar pontos de coleta:", error)

            return res.status(500).json({
                erro: "Erro ao buscar pontos de coleta"
            });
        }
        res.json(resultados)
    })
})

// PORT
const PORT = process.env.PORT || 3000

app.listen(PORT,"0.0.0.0",() => {
    console.log(`Servidor Leafy rodando na porta ${PORT}`)
})
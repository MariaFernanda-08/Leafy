package com.example.leafy.mvvm.model.repository

import com.example.leafy.mvvm.model.data.Residuo

class ResiduoRepository{
    private val residuos = listOf(
        Residuo(
            id = 1,
            nome = "Garrafa PET",
            codigoBarras = "789000000001",
            tipo = "Plástico",
            instrucoesDescarte = "Esvazie, lave e encaminhe para a coleta seletiva.",
            reciclavel = true,
            tempoDecomposicao = "Cerca de 400 anos",
            impactoAmbiental = "Pode permanecer por muito tempo no ambiente quando descartada incorretamente."
        ),

        Residuo(
            id = 2,
            nome = "Lata de alumínio",
            codigoBarras = "789000000002",
            tipo = "Metal",
            instrucoesDescarte = "Esvazie e encaminhe para a coleta seletiva.",
            reciclavel = true,
            tempoDecomposicao = "Cerca de 200 anos",
            impactoAmbiental = "O descarte incorreto contribui para a poluição do ambiente."
        ),

        Residuo(
            id = 3,
            nome = "Papelão",
            codigoBarras = "789000000003",
            tipo = "Papel",
            instrucoesDescarte = "Mantenha seco e encaminhe para a coleta seletiva.",
            reciclavel = true,
            tempoDecomposicao = "Cerca de 3 meses",
            impactoAmbiental = "Pode ser reciclado e reaproveitado na produção de novos materiais."
        ),

        Residuo(
            id = 4,
            nome = "Guardanapo usado",
            codigoBarras = null,
            tipo = "Orgânico",
            instrucoesDescarte = "Descarte conforme as orinteações locais para resíduos não recicláveis.",
            reciclavel = false,
            tempoDecomposicao = "Cerca de 1 a 3 meses",
            impactoAmbiental = "Quando descartado incorretamente, pode contribuir para a poluição."
        )
    )
    fun pesquisar(nome:String): List<Residuo>{
        if(nome.isBlank()){
            return  emptyList()
        }
        return residuos.filter {
            it.nome.contains(nome, ignoreCase = true)
        }
    }
    fun buscarPorId(id: Int): Residuo?{
        return residuos.find{
            it.id == id
        }
    }
}
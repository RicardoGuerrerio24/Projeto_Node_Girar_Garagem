# Notas da implementação

`LinkedNodeCircuit.rotateSimultaneously()` guarda todos os ocupantes numa cópia temporária e só depois atualiza as posições. Assim, todos os movimentos usam o mesmo estado antigo.

Os cruzamentos têm duas entradas visuais. Quando as duas estão ocupadas, `TerminalView` junta os rótulos com `&`, por exemplo:

```text
Mota & A4
```

Não são criados Nodes duplicados: existem apenas dez Nodes reais.

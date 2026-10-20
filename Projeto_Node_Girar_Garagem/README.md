# Três rodas com movimento simultâneo e colisões

Projeto Maven para simular Nodes únicos a circular por três rodas sobrepostas.

## Movimento simultâneo

Em cada iteração, todos os Nodes mudam de posição ao mesmo tempo. O novo estado é calculado a partir de uma cópia do estado anterior:

```text
novo[i] = estadoAnterior[i - 1]
```

Não existe `carry` nem substituição sequencial.

## Percurso

O circuito de movimento tem 12 entradas visuais:

```text
1 -> 2 -> C -> A1 -> A2 -> A3 -> A4 -> A1
   -> B -> 2 -> 3 -> 4 -> 1
```

Existem apenas dez Nodes reais:

```text
1, 2, C, A1, A2, A3, A4, B, 3, 4
```

As duas entradas de `2` e as duas entradas de `A1` são cruzamentos visuais. Uma delas começa vazia. Quando dois Nodes chegam ao mesmo cruzamento, ambos são mostrados, por exemplo:

```text
Mota & A4
```

Os dois Nodes continuam a deslocar-se simultaneamente nas iterações seguintes.

## Rodas

```text
Bola 1: 1, 2, 3, 4
Bola 2: B, A1, C, 2
Bola 3: A2, A3, A4, A1
```

O Node `2` é uma única entidade lógica e o Node `A1` também é único. As duas entradas de cada um representam os dois lados do cruzamento no percurso.

## Veículos

```text
Node 3  -> Carro
Node B  -> Mota
Node 1  -> Barco
Node A2 -> Carro
Node C  -> Mota
```

No terminal, os Nodes com veículos mostram `Carro`, `Mota` ou `Barco`. Os Nodes sem veículos mostram os seus identificadores.

## Execução

A animação mostra apenas os círculos e repete-se até `Ctrl+C`:

```bash
mvn clean compile
mvn exec:java
```

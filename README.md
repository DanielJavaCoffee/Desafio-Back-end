# Desafio Back-end PicPay

## Objetivo

O desafio consiste em criar um sistema simplificado do PicPay, focando no fluxo de transferência de dinheiro entre dois tipos de usuários: comuns e lojistas. Ambos têm carteira com dinheiro e podem realizar transferências entre si.

### Requisitos

1. **Cadastro de Usuários:**
   - Todos os usuários (comuns e lojistas) devem fornecer Nome Completo, CPF, e-mail e Senha.
   - CPF/CNPJ e e-mails devem ser únicos no sistema, permitindo apenas um cadastro com o mesmo CPF ou endereço de e-mail.

2. **Transferência de Dinheiro:**
   - Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.
   - Lojistas só recebem transferências, não enviam dinheiro para ninguém.

3. **Validação de Saldo:**
   - Antes de realizar a transferência, é necessário validar se o usuário tem saldo suficiente.

4. **Autorização Externa:**
   - Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo. Utilize [este mock](https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc) para simular a autorização.

5. **Transações Reversíveis:**
   - A operação de transferência deve ser tratada como uma transação, sendo revertida em caso de inconsistência. O dinheiro deve voltar para a carteira do usuário que enviou.

6. **Notificação de Pagamento:**
   - Quando um usuário ou lojista receber um pagamento, é necessário enviar uma notificação (por e-mail ou SMS) utilizando um serviço de terceiros. Utilize [este mock](https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6) para simular o envio.

7. **RESTFul API:**
   - O serviço deve ser implementado como uma API RESTFul.

### Exemplo de Payload

```json
POST /transaction

{
    "value": 100.0,
    "payer": 4,
    "payee": 15
}

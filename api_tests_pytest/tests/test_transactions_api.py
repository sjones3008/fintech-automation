from api_tests_pytest.utils.data_factory import DataFactory

class TestTransactionsAPI:
    def test_create_transaction_success(self, api_client):
        payload = DataFactory.valid_transaction_payload()
        res = api_client.post("/api/transactions", payload)
        assert res.status_code == 201
        data = res.json()
        assert "transactionId" in data
        assert data["amount"] == 100.50
        assert data["type"] == "transfer"

    def test_get_user_transactions_success(self, api_client):
        res = api_client.get("/api/transactions/123")
        assert res.status_code == 200
        data = res.json()
        assert isinstance(data, list)

    def test_create_transaction_zero_or_negative_amount(self, api_client):
        payload = DataFactory.valid_transaction_payload(amount=-50.00)
        res = api_client.post("/api/transactions", payload)
        assert res.status_code == 400

    def test_create_transaction_invalid_recipient(self, api_client):
        payload = DataFactory.valid_transaction_payload(recipient_id="invalid_acc")
        res = api_client.post("/api/transactions", payload)
        assert res.status_code == 404

    def test_get_transactions_unauthorized(self, api_client):
        res = api_client.get("/api/transactions/123", headers={})
        assert res.status_code in [401, 403]
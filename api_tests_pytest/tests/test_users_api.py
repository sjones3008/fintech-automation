import pytest
from api_tests_pytest.utils.data_factory import valid_user_payload

class TestUsersAPI:
    def test_create_user_success(self, api_client):
        payload = valid_user_payload()
        res = api_client.post("/api/users", payload)
        assert res.status_code == 201
        data = res.json()
        assert "id" in data
        assert data["name"] == payload["name"]
        assert data["email"] == payload["email"]
        assert data["accountType"] == payload["accountType"]

    def test_get_user_details_success(self, api_client):
        res = api_client.get("/api/users/123")
        assert res.status_code == 200
        data = res.json()
        assert data["id"] == "123"
        assert "name" in data
        assert "email" in data

    @pytest.mark.parametrize("missing_field", ["name", "email", "accountType"])
    def test_create_user_missing_required_fields(self, api_client, missing_field):
        payload = valid_user_payload()
        del payload[missing_field]
        res = api_client.post("/api/users", payload)
        assert res.status_code == 400
        assert "error" in res.json()

    def test_create_user_invalid_email_format(self, api_client):
        payload = valid_user_payload()
        payload["email"] = "invalid-email-format"
        res = api_client.post("/api/users", payload)
        assert res.status_code == 400

    def test_get_user_not_found(self, api_client):
        res = api_client.get("/api/users/non_existent_9999")
        assert res.status_code == 404

    def test_create_user_unauthorized(self, api_client):
        res = api_client.post("/api/users", DataFactory.valid_user_payload(), headers={})
        assert res.status_code in [401, 403]
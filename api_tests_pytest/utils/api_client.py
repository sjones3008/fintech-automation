import requests
import logging
from api_tests_pytest.config import Config

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger("APIClient")

class APIClient:
    def __init__(self):
        self.base_url = Config.BASE_URL
        self.headers = {
            "Content-Type": "application/json",
            "Authorization": Config.AUTH_TOKEN
        }

    def _log_interaction(self, response):
        logger.info(f"Request URL: {response.request.url}")
        logger.info(f"Request Body: {response.request.body}")
        logger.info(f"Status Code: {response.status_code}")
        logger.info(f"Response Body: {response.text}")

    def post(self, endpoint, payload=None, headers=None):
        url = f"{self.base_url}{endpoint}"
        req_headers = headers if headers is not None else self.headers
        res = requests.post(url, json=payload, headers=req_headers, timeout=Config.TIMEOUT)
        self._log_interaction(res)
        return res

    def get(self, endpoint, headers=None):
        url = f"{self.base_url}{endpoint}"
        req_headers = headers if headers is not None else self.headers
        res = requests.get(url, headers=req_headers, timeout=Config.TIMEOUT)
        self._log_interaction(res)
        return res
import os
from dotenv import load_dotenv

env = os.getenv("ENV", "staging")
load_dotenv(f".env.{env}")

class Config:
    BASE_URL = os.getenv("BASE_URL", "http://localhost:3000")
    AUTH_TOKEN = os.getenv("AUTH_TOKEN", "Bearer mock-secret-token")
    TIMEOUT = int(os.getenv("TIMEOUT", "10"))

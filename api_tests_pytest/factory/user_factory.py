import uuid

def generate_user_data(overrides=None):
    payload = {
        "name": f"Test User {uuid.uuid4().hex[:6]}",
        "email": f"user_{uuid.uuid4().hex[:6]}@example.com",
        "accountType": "premium"
    }
    if overrides:
        payload.update(overrides)
    return payload
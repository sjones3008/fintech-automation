import pytest
import logging
import os
from datetime import datetime

from api_tests_pytest.utils.api_client import APIClient

@pytest.fixture(scope="session")
def api_client():
    return APIClient()

# Ensure logs directory exists
os.makedirs("api_reports/logs", exist_ok=True)

logging.basicConfig(
    filename=f"api_reports/logs/api_execution_{datetime.now().strftime('%Y%m%d_%H%M%S')}.log",
    level=logging.INFO,
    format="%(asctime)s [%(levelname)s] %(name)s: %(message)s",
)

@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    """Custom hook to attach request/response metadata to the pytest-html report."""
    outcome = yield
    report = outcome.get_result()
    extra = getattr(report, "extra", [])

    if report.when == "call":
        # Always log test outcome
        logging.info(f"Test: {item.name} | Result: {report.outcome.upper()}")
        report.extra = extra

def pytest_html_report_title(report):
    report.title = "Fintech API Test Execution Report"